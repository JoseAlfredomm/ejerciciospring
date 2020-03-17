package com.ejercicio.ejercicio.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ejercicio.ejercicio.entity.Ticket;

public interface TicketRepository  extends PagingAndSortingRepository<Ticket, Integer>   {
	
}
