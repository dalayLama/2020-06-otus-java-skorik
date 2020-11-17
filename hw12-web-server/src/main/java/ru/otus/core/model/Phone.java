package ru.otus.core.model;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "_number")
    private String number;

    public Phone() {
    }

    public Phone(User user, String number) {
        this.user = user;
        this.number = number;
    }

    public Phone(long id, User user, String number) {
        this.id = id;
        this.user = user;
        this.number = number;
    }

    public Phone(User user, Dto dto) {
        this.id = dto.getId();
        this.user = user;
        this.number = dto.getNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static class Dto {

        private Long id;

        private String number;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

}
