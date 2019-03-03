package dao;

import model.Tag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TagDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public void addTag(Tag tag){
        em.persist(tag);
    }

    public void editTag(Tag tag){
        em.merge(tag);
    }

    public List<Tag> getAllTags(){
        Query query = em.createNamedQuery("Tags.getAll", Tag.class);
        return query.getResultList();
    }
}
