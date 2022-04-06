package com.tfl.food2forkkmm.domain.util

import com.tfl.food2forkkmm.domain.model.GenericMessageInfo

class GenericMessageQueueUtil() {

    fun doesMessageAlreadyExistInQueue(
        queue: Queue<GenericMessageInfo>,
        messageInfo: GenericMessageInfo
    ): Boolean {
        for (item in queue.items) {
            if (item.id == messageInfo.id) {
                return true
            }
        }
        return false
    }
}