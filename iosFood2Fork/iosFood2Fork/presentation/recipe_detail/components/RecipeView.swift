//
//  RecipeView.swift
//  iosFood2Fork
//
//  Created by Viktor Yamchinov on 19.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SDWebImageSwiftUI
import shared

struct RecipeView: View {
    
    @State var showDialog: Bool
    
    private let recipe: Recipe?
    private let dateUtil: DateTimeUtil
    private let message: GenericMessageInfo?
    private let onTriggerEvent: (RecipeDetailEvents) -> Void
    
    init(recipe: Recipe, dateUtil: DateTimeUtil, message: GenericMessageInfo? = nil, onTriggerEvent: @escaping (RecipeDetailEvents) -> Void) {
        self.recipe = recipe
        self.dateUtil = dateUtil
        self.message = message
        if message != nil {
            self.showDialog = true
        } else {
            self.showDialog = false
        }
        self.onTriggerEvent = onTriggerEvent
    }
    
    var body: some View {
        NavigationView {
            ScrollView {
                if recipe == nil {
                    Text("Error")
                } else {
                    VStack(alignment: .leading) {
                        WebImage(url: URL(string: recipe!.featuredImage))
                            .resizable()
                            .placeholder {
                                Rectangle().foregroundColor(.white)
                            }
                            .indicator(.activity)
                            .transition(.fade(duration: 0.5))
                            .scaledToFill()
                            .frame(height: 250, alignment: .center)
                            .clipped()
                        VStack(alignment: .leading) {
                            HStack(alignment: .lastTextBaseline) {
                                DefaultText("Updated \(dateUtil.humanizeDatetime(date: recipe!.dateUpdated)) by \(recipe!.publisher)")
                                    .foregroundColor(Color.gray)
                                Spacer()
                                DefaultText(String(recipe!.rating))
                                    .frame(alignment: .trailing)
                            }
                            ForEach(recipe!.ingredients as Array<String>, id: \.self) { ingredient in
                                DefaultText(ingredient, size: 16)
                                    .padding(.top, 4)
                            }
                        }
                        .background(Color.white)
                        .padding(12)
                    }
                }
                
            }
            .navigationBarHidden(true)
            .alert(isPresented: $showDialog, content: {
                return GenericMessageInfoAlert().build(message: message!) {
                    onTriggerEvent(RecipeDetailEvents.OnRemoveHeadMessageFromQueue())
                }
            })
        }
        .navigationBarTitle(Text(recipe?.title ?? "Error"), displayMode: .inline)
    }
}

//struct RecipeView_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeView()
//    }
//}
