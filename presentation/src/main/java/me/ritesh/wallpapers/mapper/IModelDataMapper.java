package me.ritesh.wallpapers.mapper;

/**
 * @author Ritesh Shakya
 */

public interface IModelDataMapper<K, M> {
    M transform(K response);
}
