package com.endava.bookrental.modeltests;

import com.endava.bookrental.models.WaitingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class WaitingListModelTest {
    private WaitingList waitingList;

    @BeforeEach
    void initializeWaitingList(){
        waitingList=new WaitingList();
    }

    @Test
    void setWaitingIdTest(){
        waitingList.setWaitingId(1);
        assertThat(waitingList.getWaitingId()).isEqualTo(1);
    }

    @Test
    void setBookIdTest(){
        waitingList.setBookId(1);
        assertThat(waitingList.getBookId()).isEqualTo(1);
    }

    @Test
    void setUserIdTest(){
        waitingList.setWaiterId(1);
        assertThat(waitingList.getWaiterId()).isEqualTo(1);
    }
}
