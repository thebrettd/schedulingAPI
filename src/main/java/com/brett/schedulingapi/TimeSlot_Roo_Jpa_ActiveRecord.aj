// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.brett.schedulingapi;

import com.brett.schedulingapi.TimeSlot;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TimeSlot_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TimeSlot.entityManager;
    
    public static final List<String> TimeSlot.fieldNames4OrderClauseFilter = java.util.Arrays.asList("slotDate", "capacity", "capacityUsed", "cost");
    
    public static final EntityManager TimeSlot.entityManager() {
        EntityManager em = new TimeSlot().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TimeSlot.countTimeSlots() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TimeSlot o", Long.class).getSingleResult();
    }
    
    public static List<TimeSlot> TimeSlot.findAllTimeSlots() {
        return entityManager().createQuery("SELECT o FROM TimeSlot o", TimeSlot.class).getResultList();
    }
    
    public static List<TimeSlot> TimeSlot.findAllTimeSlots(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TimeSlot o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TimeSlot.class).getResultList();
    }
    
    public static TimeSlot TimeSlot.findTimeSlot(Long id) {
        if (id == null) return null;
        return entityManager().find(TimeSlot.class, id);
    }
    
    public static List<TimeSlot> TimeSlot.findTimeSlotEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TimeSlot o", TimeSlot.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<TimeSlot> TimeSlot.findTimeSlotEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TimeSlot o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TimeSlot.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TimeSlot.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TimeSlot.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TimeSlot attached = TimeSlot.findTimeSlot(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TimeSlot.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TimeSlot.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TimeSlot TimeSlot.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TimeSlot merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
