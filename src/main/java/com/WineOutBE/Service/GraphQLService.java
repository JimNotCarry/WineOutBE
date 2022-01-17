package com.WineOutBE.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:graphql/schema.graphqls")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private UserDataFetcher userDataFetcher;

    @Autowired
    private AllUsersDataFetcher allUsersDataFetcher;

    @PostConstruct
    private void init() throws IOException {

        File schemaFile = resource.getFile();

        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);

        RuntimeWiring wiring = buildRuntimeWiring();

        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);

        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                        typeWiring
                                .dataFetcher("allUsers", allUsersDataFetcher)
                                .dataFetcher("user", userDataFetcher))
                .build();
    };

    @Bean
    public GraphQL getGraphQL() {
        return graphQL;
    }
}
