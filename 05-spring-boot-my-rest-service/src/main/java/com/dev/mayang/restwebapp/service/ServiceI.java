package com.dev.mayang.restwebapp.service;

import java.util.List;

public interface ServiceI<T> {

    List<T> findAll();
}