package org.jugvale.call4papers.rest.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jugvale.call4papers.model.Autor;
import org.jugvale.call4papers.rest.RestAbstrato;
import org.jugvale.call4papers.service.impl.AutorService;

/**
 * 
 */
@Path("/autores")
public class AutorEndpoint extends RestAbstrato<Autor>{

	@Inject
	AutorService service;

	@Override
	public Response criar(Autor entidade) {
		service.salvar(entidade);
		return Response.created(
				UriBuilder.fromResource(AutorEndpoint.class)
				.path(String.valueOf(entidade.getId())).build()).build();
	}

	@Override
	public void apagaPorId(@PathParam("id") Long id) {
		Autor autor = service.buscarPorId(id);
		service.remover(verificaSeEhNulo(autor, id));
	}

	@Override
	public Autor buscaPorId(@PathParam("id") Long id) {
		Autor autor = service.buscarPorId(id);
		return verificaSeEhNulo(autor, id);
	}

	@Override
	public List<Autor> listarTodos() {
		return service.buscaTodos();
	}

	@Override
	public void atualizar(@PathParam("id") long id, Autor novoAutor) {
		verificaSeEhNulo(buscaPorId(id), id);
		novoAutor.setId(id);
		service.atualizar(novoAutor);
	}
}
