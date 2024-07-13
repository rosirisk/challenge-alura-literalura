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
public class Translator {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private long birth_year;

    @Column
    private long death_year;

    @ManyToMany(mappedBy = "translators")
    private List<Book> books;

    public Translator(PersonRecord record) {
        this.name =record.name();
        this.birth_year =record.birth_year();
        this.death_year =record.death_year();
    }

}
