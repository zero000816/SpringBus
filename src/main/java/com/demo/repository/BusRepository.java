package com.demo.repository;

import com.demo.entity.Bus;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.LockModeType;

@Repository
public interface BusRepository extends JpaRepository<Bus , Long> {

    Bus findByBid (Integer bid);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select bus FROM Bus bus WHERE bus.bid=?1")
    Bus findByBidForUpdate(Integer bid);

    //预定车上座位
    @Modifying
    @Query("update Bus set sale=sale+1,number=number-1 where bid=?1")
    int updateSale(Integer bid);

}
