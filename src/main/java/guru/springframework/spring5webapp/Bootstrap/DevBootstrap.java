package guru.springframework.spring5webapp.Bootstrap;

import guru.springframework.spring5webapp.Model.Author;
import guru.springframework.spring5webapp.Model.Book;
import guru.springframework.spring5webapp.Model.Publisher;
import guru.springframework.spring5webapp.Repositories.AuthorRepository;
import guru.springframework.spring5webapp.Repositories.BookRepository;
import guru.springframework.spring5webapp.Repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();

    }

    private void initData()
    {
        Publisher p = new Publisher();
        p.setName("Jack");

        publisherRepository.save(p);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book( "Domain Driven Design","1234", p);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod =new Author("Rod", "Johnson");
        Book noEJB = new Book("No J2EE from now on", "23444",p);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }


}
