package br.com.literalura.model.entity;

import br.com.literalura.model.PersonRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private long birthYear;

    @Column
    private long deathYear;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(PersonRecord record) {
        this.name =record.name();
        this.birthYear =record.birth_year();
        this.deathYear =record.death_year();
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + name + '\'' +
                ",ano de nascimento=" + birthYear +
                ",ano de falecimento=" + deathYear +
                "}\n";
    }
}
