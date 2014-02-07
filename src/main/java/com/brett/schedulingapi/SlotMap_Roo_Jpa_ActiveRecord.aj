// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.brett.schedulingapi;

import com.brett.schedulingapi.SlotMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SlotMap_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SlotMap.entityManager;
    
    public static final List<String> SlotMap.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager SlotMap.entityManager() {
        EntityManager em = new SlotMap().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SlotMap.countSlotMaps() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SlotMap o", Long.class).getSingleResult();
    }
    
    public static List<SlotMap> SlotMap.findAllSlotMaps() {
        return entityManager().createQuery("SELECT o FROM SlotMap o", SlotMap.class).getResultList();
    }
    
    public static List<SlotMap> SlotMap.findAllSlotMaps(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SlotMap o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SlotMap.class).getResultList();
    }
    
    public static SlotMap SlotMap.findSlotMap(Long id) {
        if (id == null) return null;
        return entityManager().find(SlotMap.class, id);
    }
    
    public static List<SlotMap> SlotMap.findSlotMapEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SlotMap o", SlotMap.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<SlotMap> SlotMap.findSlotMapEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SlotMap o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SlotMap.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SlotMap.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SlotMap.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SlotMap attached = SlotMap.findSlotMap(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SlotMap.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SlotMap.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SlotMap SlotMap.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SlotMap merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}