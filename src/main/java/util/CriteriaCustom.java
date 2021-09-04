package util;

import javax.persistence.criteria.CriteriaBuilder;

public final class CriteriaCustom {

    private static CriteriaBuilder criteriaBuilder;

    private CriteriaCustom(){
    }

    public static synchronized CriteriaBuilder getCriteriaBuilderCutom(){
        if(criteriaBuilder == null){
            criteriaBuilder = HibernateUtil.getEntityManagerFactory().createEntityManager().getCriteriaBuilder();
        }
        return criteriaBuilder;
    }
}
