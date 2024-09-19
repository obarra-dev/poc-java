package com.obarra.pocjdk8;

import java.util.Date;
import java.util.Objects;

public class Car
{
    public Date publishedAt;

    public Car(final Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(final Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(publishedAt, car.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(publishedAt);
    }
}
