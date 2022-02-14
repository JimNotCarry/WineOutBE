package com.WineOutBE.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Autowired
    private UserDatafetcher userDataFetcher;

    @Autowired
    private AllUsersDatafetcher allUsersDatafetcher;

    @Autowired
    private CreateUserDataFetcher createUserDataFetcher;

    @Autowired
    private DiaryPostUserDataFetcher diaryPostUserDataFetcher;

    @Value("classpath:/graphql/user.graphqls")
    private Resource resource;

    private GraphQL graphQL;

    @PostConstruct
    private void loadSchema() throws IOException {
        File schema = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schema);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring  buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                    typeWiring
                            .dataFetcher("GetUser", userDataFetcher)
                            .dataFetcher("GetAllUsers", allUsersDatafetcher)
                )
                .type("Mutation", typeWiring ->
                        typeWiring
                                .dataFetcher("CreateUser", createUserDataFetcher)
                                .dataFetcher("DiaryPost", diaryPostUserDataFetcher)
                )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}