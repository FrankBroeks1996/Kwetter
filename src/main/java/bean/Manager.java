package bean;

import ejb.ServiceBean;
import model.SimpleProperty;
import producer.Producer;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.inject.Named;

@Model
public class Manager {

    @Inject
    ServiceBean ejb;

    @Produces
    @Named
    SimpleProperty property;

    @Inject
    Producer producer;

    @PostConstruct
    public void initNewProperty() {
        property = new SimpleProperty();
    }

    public void save() {
        ejb.put(property);
        initNewProperty();
    }

    public void clear(SimpleProperty property) {
        ejb.delete(property);
    }
}