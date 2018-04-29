package com.learning.nerus.spring5webapp.bootstrap;

import com.learning.nerus.spring5webapp.model.Author;
import com.learning.nerus.spring5webapp.model.Book;
import com.learning.nerus.spring5webapp.model.Publisher;
import com.learning.nerus.spring5webapp.repositories.AuthorRepository;
import com.learning.nerus.spring5webapp.repositories.BookRepository;
import com.learning.nerus.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private AuthorRepository authRepo;
  private BookRepository bookRepo;
  private PublisherRepository pubRepo;


  public DevBootstrap(AuthorRepository authRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authRepo = authRepository;
    this.bookRepo = bookRepository;
    this.pubRepo = publisherRepository;
  }

  private void initData() {

    Publisher daveBooks = new Publisher("Dave Book Publishing", "Maradana colombo 2");

    Author suren = new Author("Suren", "Rodrigo");
    Book book1 = new Book("Domain Driven Design", "8893848", daveBooks);

    suren.getBooks().add(book1);
    book1.getAuthors().add(suren);

    this.pubRepo.save(daveBooks);
    this.authRepo.save(suren);
    this.bookRepo.save(book1);

    Publisher gandhiBooks = new Publisher("Gandhi Book Publishing", "Mahara Kadawatha");
    Author dinuka = new Author("Dinuka", "Rodrigo");
    Book book2 = new Book("Facebook for Saree selling", "48583958", gandhiBooks);
    dinuka.getBooks().add(book2);

    this.pubRepo.save(gandhiBooks);
    this.authRepo.save(dinuka);
    this.bookRepo.save(book2);

  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
  }
}
