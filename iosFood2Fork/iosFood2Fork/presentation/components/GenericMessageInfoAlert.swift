//
//  GenericMessageInfoAlert.swift
//  iosFood2Fork
//
//  Created by Viktor Yamchinov on 20.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GenericMessageInfoAlert {
    
    func build(message: GenericMessageInfo, onRemoveHeadMessage: @escaping () -> Void) -> Alert {
        return Alert(
            title: Text(message.title),
            message: Text(message.description_ ?? "Something went wrong"),
            primaryButton: .default(
                Text(message.positiveAction?.positiveBtnTxt ?? "OK"),
                action: {
                    if (message.positiveAction != nil) {
                        message.positiveAction!.onPositiveAction()
                    }
                    onRemoveHeadMessage()
                }
            ),
            secondaryButton: .default(
                Text(message.negativeAction?.negativeBtnTxt ?? "Cancel"),
                action: {
                    if (message.negativeAction != nil) {
                        message.negativeAction!.onNegativeAction()
                    }
                    onRemoveHeadMessage()
                }
            ))
    }
}

