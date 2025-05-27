package com.example.pingpong;

class Snowflake {
    public float x, y;
    public float speed;
    public float size;
    public float opacity;

    public Snowflake(float x, float y, float speed, float size) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.opacity = 0.7f + (float)Math.random() * 0.3f; // 0.7 ~ 1.0
    }
}