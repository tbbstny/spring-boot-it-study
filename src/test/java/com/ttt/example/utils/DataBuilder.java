package com.ttt.example.utils;

/**
 * DataBuilder Interface defines our common test data builder pattern.
 * @param <Entity> The Entity type to build test data for
 * @param <Builder> The Entity builder (i.e. lombok @Builder annotation in Entity)
 */
public interface DataBuilder<Entity, Builder>
{
    int dataRowCount();
    Object[] getTestData(int testDataIndex);
    void resetDB();
    Builder create(int testDataIndex);
    void addAll();
    Entity persist(Entity entity);
}
