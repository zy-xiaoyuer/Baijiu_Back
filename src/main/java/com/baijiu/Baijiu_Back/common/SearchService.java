package com.baijiu.Baijiu_Back.common;

import java.util.List;

public interface SearchService<T> {
    List<T> search(String keyword);
}
