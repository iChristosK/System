/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Christos
 */
@Entity
@Table(name = "contract", catalog = "kios", schema = "")
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "SELECT c FROM Contract c")
    , @NamedQuery(name = "Contract.findByIDcontract", query = "SELECT c FROM Contract c WHERE c.iDcontract = :iDcontract")
    , @NamedQuery(name = "Contract.findByFkIDresearcher", query = "SELECT c FROM Contract c WHERE c.fkIDresearcher = :fkIDresearcher")
    , @NamedQuery(name = "Contract.findByFkIDproject", query = "SELECT c FROM Contract c WHERE c.fkIDproject = :fkIDproject")
    , @NamedQuery(name = "Contract.findByPosition", query = "SELECT c FROM Contract c WHERE c.position = :position")
    , @NamedQuery(name = "Contract.findByDetails", query = "SELECT c FROM Contract c WHERE c.details = :details")
    , @NamedQuery(name = "Contract.findByCFrom", query = "SELECT c FROM Contract c WHERE c.cFrom = :cFrom")
    , @NamedQuery(name = "Contract.findByCTo", query = "SELECT c FROM Contract c WHERE c.cTo = :cTo")
    , @NamedQuery(name = "Contract.findBySalary", query = "SELECT c FROM Contract c WHERE c.salary = :salary")
    , @NamedQuery(name = "Contract.findByMonthlyHours", query = "SELECT c FROM Contract c WHERE c.monthlyHours = :monthlyHours")
    , @NamedQuery(name = "Contract.findByHourlyRate", query = "SELECT c FROM Contract c WHERE c.hourlyRate = :hourlyRate")})
public class Contract implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_contract")
    private Integer iDcontract;
    @Basic(optional = false)
    @Column(name = "fk_ID_researcher")
    private String fkIDresearcher;
    @Basic(optional = false)
    @Column(name = "fk_ID_project")
    private int fkIDproject;
    @Basic(optional = false)
    @Column(name = "Position")
    private String position;
    @Column(name = "Details")
    private String details;
    @Basic(optional = false)
    @Column(name = "c_From")
    @Temporal(TemporalType.DATE)
    private Date cFrom;
    @Basic(optional = false)
    @Column(name = "c_To")
    @Temporal(TemporalType.DATE)
    private Date cTo;
    @Basic(optional = false)
    @Column(name = "Salary")
    private float salary;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Monthly_Hours")
    private Float monthlyHours;
    @Column(name = "Hourly_Rate")
    private Float hourlyRate;

    public Contract() {
    }

    public Contract(Integer iDcontract) {
        this.iDcontract = iDcontract;
    }

    public Contract(Integer iDcontract, String fkIDresearcher, int fkIDproject, String position, Date cFrom, Date cTo, float salary) {
        this.iDcontract = iDcontract;
        this.fkIDresearcher = fkIDresearcher;
        this.fkIDproject = fkIDproject;
        this.position = position;
        this.cFrom = cFrom;
        this.cTo = cTo;
        this.salary = salary;
    }

    public Integer getIDcontract() {
        return iDcontract;
    }

    public void setIDcontract(Integer iDcontract) {
        Integer oldIDcontract = this.iDcontract;
        this.iDcontract = iDcontract;
        changeSupport.firePropertyChange("IDcontract", oldIDcontract, iDcontract);
    }

    public String getFkIDresearcher() {
        return fkIDresearcher;
    }

    public void setFkIDresearcher(String fkIDresearcher) {
        String oldFkIDresearcher = this.fkIDresearcher;
        this.fkIDresearcher = fkIDresearcher;
        changeSupport.firePropertyChange("fkIDresearcher", oldFkIDresearcher, fkIDresearcher);
    }

    public int getFkIDproject() {
        return fkIDproject;
    }

    public void setFkIDproject(int fkIDproject) {
        int oldFkIDproject = this.fkIDproject;
        this.fkIDproject = fkIDproject;
        changeSupport.firePropertyChange("fkIDproject", oldFkIDproject, fkIDproject);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        String oldPosition = this.position;
        this.position = position;
        changeSupport.firePropertyChange("position", oldPosition, position);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        String oldDetails = this.details;
        this.details = details;
        changeSupport.firePropertyChange("details", oldDetails, details);
    }

    public Date getCFrom() {
        return cFrom;
    }

    public void setCFrom(Date cFrom) {
        Date oldCFrom = this.cFrom;
        this.cFrom = cFrom;
        changeSupport.firePropertyChange("CFrom", oldCFrom, cFrom);
    }

    public Date getCTo() {
        return cTo;
    }

    public void setCTo(Date cTo) {
        Date oldCTo = this.cTo;
        this.cTo = cTo;
        changeSupport.firePropertyChange("CTo", oldCTo, cTo);
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        float oldSalary = this.salary;
        this.salary = salary;
        changeSupport.firePropertyChange("salary", oldSalary, salary);
    }

    public Float getMonthlyHours() {
        return monthlyHours;
    }

    public void setMonthlyHours(Float monthlyHours) {
        Float oldMonthlyHours = this.monthlyHours;
        this.monthlyHours = monthlyHours;
        changeSupport.firePropertyChange("monthlyHours", oldMonthlyHours, monthlyHours);
    }

    public Float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Float hourlyRate) {
        Float oldHourlyRate = this.hourlyRate;
        this.hourlyRate = hourlyRate;
        changeSupport.firePropertyChange("hourlyRate", oldHourlyRate, hourlyRate);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDcontract != null ? iDcontract.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.iDcontract == null && other.iDcontract != null) || (this.iDcontract != null && !this.iDcontract.equals(other.iDcontract))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testpackage.Contract[ iDcontract=" + iDcontract + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
