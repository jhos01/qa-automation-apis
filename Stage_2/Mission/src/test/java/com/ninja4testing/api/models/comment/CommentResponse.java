package com.ninja4testing.api.models.comment;

public class CommentResponse {
    private Integer id;
    private Integer post_id;
    private String name;
    private String email;
    private String body;

    public CommentResponse(Integer id, String body, String email, String name, Integer post_id) {
        this.id = id;
        this.body = body;
        this.email = email;
        this.name = name;
        this.post_id = post_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
