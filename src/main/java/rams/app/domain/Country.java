/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rams.app.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ramazan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")})
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "code_country", nullable = false, length = 3)
    private String codeCountry;
    @Size(max = 52)
    @Column(name = "country_name", length = 52)
    private String countryName;
    @Size(max = 13)
    @Column(name = "country_continent", length = 13)
    private String countryContinent;
    @OneToMany(mappedBy = "promoterCountry")
    private Collection<Promoter> promoterCollection;
    @OneToMany(mappedBy = "countryCode")
    private Collection<City> cityCollection;
    @OneToMany(mappedBy = "eventCountry")
    private Collection<EventTable> eventTableCollection;
    @OneToMany(mappedBy = "sanctionerCountry")
    private Collection<Sanctioner> sanctionerCollection;
    @OneToMany(mappedBy = "fightCountry")
    private Collection<Fighter> fighterCollection;

    public Country() {
    }

    public Country(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryContinent() {
        return countryContinent;
    }

    public void setCountryContinent(String countryContinent) {
        this.countryContinent = countryContinent;
    }

    public Collection<Promoter> getPromoterCollection() {
        return promoterCollection;
    }

    public void setPromoterCollection(Collection<Promoter> promoterCollection) {
        this.promoterCollection = promoterCollection;
    }

    public Collection<City> getCityCollection() {
        return cityCollection;
    }

    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }

    public Collection<EventTable> getEventTableCollection() {
        return eventTableCollection;
    }

    public void setEventTableCollection(Collection<EventTable> eventTableCollection) {
        this.eventTableCollection = eventTableCollection;
    }

    public Collection<Sanctioner> getSanctionerCollection() {
        return sanctionerCollection;
    }

    public void setSanctionerCollection(Collection<Sanctioner> sanctionerCollection) {
        this.sanctionerCollection = sanctionerCollection;
    }

    public Collection<Fighter> getFighterCollection() {
        return fighterCollection;
    }

    public void setFighterCollection(Collection<Fighter> fighterCollection) {
        this.fighterCollection = fighterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeCountry != null ? codeCountry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.codeCountry == null && other.codeCountry != null) || (this.codeCountry != null && !this.codeCountry.equals(other.codeCountry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return countryName + "  " + codeCountry ;
    }
    
}
