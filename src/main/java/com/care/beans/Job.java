
package com.care.beans;

import java.util.Date;

public class Job {
        private int id;
        private String title;
        private int postedBy;
        private Date startDate;
        private Date endDate;
        private String status;
        private Date dateOfCreation;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public int getPostedBy() {
                return postedBy;
        }

        public void setPostedBy(int postedBy) {
                this.postedBy = postedBy;
        }

        public Date getStartDate() {
                return startDate;
        }

        public void setStartDate(Date startDate) {
                this.startDate = startDate;
        }

        public Date getEndDate() {
                return endDate;
        }

        public void setEndDate(Date endDate) {
                this.endDate = endDate;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public Date getDateOfCreation() {
                return dateOfCreation;
        }

        public void setDateOfCreation(Date dateOfCreation) {
                this.dateOfCreation = dateOfCreation;
        }
}
