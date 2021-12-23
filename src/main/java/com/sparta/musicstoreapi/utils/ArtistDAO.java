package com.sparta.musicstoreapi.utils;

import com.sparta.musicstoreapi.entities.Album;
import com.sparta.musicstoreapi.entities.Artist;
import com.sparta.musicstoreapi.entities.Playlist;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistDAO {
    @Autowired
    EntityManager entityManager;

    public List<Artist> searchQuery (String s) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        List<Artist> listArtist = new ArrayList<>();

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Artist.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
//                .keyword()
                .simpleQueryString()
//                .wildcard()
                .onFields("name", "title")
                .withAndAsDefaultOperator()
                .matching(s)
                .createQuery();

        FullTextQuery jpaQuery = (FullTextQuery) fullTextEntityManager.createFullTextQuery(luceneQuery, Artist.class, Album.class, Playlist.class);

        System.out.println(jpaQuery);
        listArtist = jpaQuery.getResultList();
        return listArtist;
    }
}