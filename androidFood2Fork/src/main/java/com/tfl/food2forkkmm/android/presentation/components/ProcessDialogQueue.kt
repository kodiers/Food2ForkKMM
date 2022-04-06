package com.tfl.food2forkkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.tfl.food2forkkmm.domain.model.GenericMessageInfo
import com.tfl.food2forkkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?,
    onRemoveHeadFromQueue: () -> Unit
) {
    dialogQueue?.peek()?.let { dialogInfo -> GenericDialog(
        title = dialogInfo.title,
        description = dialogInfo.description,
        onRemoveHeadFromQueue = onRemoveHeadFromQueue,
        onDismiss = dialogInfo.onDismiss,
        positiveAction = dialogInfo.positiveAction,
        negativeAction = dialogInfo.negativeAction
    ) }
}