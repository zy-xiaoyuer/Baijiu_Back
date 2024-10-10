package com.baijiu.Baijiu_Back.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface SearchService<T> {
    IPage<T> search(String keyword, Page<T> page);
    public long count(String keyword);
}
