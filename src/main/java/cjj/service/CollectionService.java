package cjj.service;

import cjj.common.Result;

public interface CollectionService {
    Result sumCollection(String username);
    Result getMyCol(int userId);
}
