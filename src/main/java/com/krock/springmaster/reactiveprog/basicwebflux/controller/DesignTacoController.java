package com.krock.springmaster.reactiveprog.basicwebflux.controller;

import com.krock.springmaster.reactiveprog.basicwebflux.data.TacoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Configuration
public class DesignTacoController {

	@Autowired
	private TacoRepository tacoRepo;

	public RouterFunction<?> routerFunction(){
		return RouterFunctions.route(RequestPredicates.GET("/taco/design"), this::recents)
			.andRoute(RequestPredicates.POST("/design"),this::postTaco);
	}


	private Mono<ServerResponse> recents(ServerRequest serverRequest){
		return ServerResponse.ok().body(tacoRepo.findAll().take(12), Taco.class);
	}
}
