package br.com.literalura.model;

import java.util.List;
import java.util.Map;

public record Book(long id, String title, List<String> subjects, List<Person> authors,List<Person> translators, List<String> bookshelves, List<String> languages,Boolean copyright, String media_type, Map<String, String> formats, long download_count
) {

}
