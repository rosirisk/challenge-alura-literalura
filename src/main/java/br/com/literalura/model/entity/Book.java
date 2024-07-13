package br.com.literalura.model.entity;

import br.com.literalura.model.BookRecord;
import br.com.literalura.model.PersonRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Column

    @Id
    private Long id;

    @Column
    private String title;

    @ElementCollection
    @CollectionTable(name="listOfSubjects")
    private List<String> subjects;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "author_books",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")}
    )
    private List<Author> authors;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "translator_books",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "translator_id", referencedColumnName = "id")}
    )
    private List<Translator> translators;

    @ElementCollection
    @CollectionTable(name="listOfBookshelves")
    private List<String> bookshelves;

    @ElementCollection
    @CollectionTable(name="listOfLanguages")
    private List<String> languages;

    @Column
    private Boolean copyright;

    @Column
    private String media_type;

    @OneToMany(targetEntity = Formats.class, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Formats> formats;

    @Column
    private long download_count;


    public Book(BookRecord record) {
        List<PersonRecord> authors = record.authors();
        List<PersonRecord> translators = record.translators();
        Map<String, String> formats = record.formats();

        List<Author> authorList = authors
                .stream()
                .map(a -> new Author(a)).collect(Collectors.toList());

        List<Translator> translatorsList = translators
                .stream()
                .map(t -> new Translator(t)).collect(Collectors.toList());

        List<Formats> formatsList = formats.entrySet().stream()
                .map((e) -> new Formats(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        this.id = record.id();
        this.title = record.title();
        this.subjects = record.subjects();
        this.authors = authorList;
        this.translators = translatorsList;
        this.bookshelves = record.bookshelves();
        this.languages = record.languages();
        this.copyright = record.copyright();
        this.media_type = record.media_type();
        this.formats = formatsList;
        this.download_count = record.download_count();
    }
}
