//
//  RecipeListViewModel.swift
//  iosFood2Fork
//
//  Created by Viktor Yamchinov on 08.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil
    
    @Published var state: RecipeListState = RecipeListState()
    
    init(searchRecipes: SearchRecipes, foodCategoryUtil: FoodCategoryUtil) {
        self.searchRecipes = searchRecipes
        self.foodCategoryUtil = foodCategoryUtil
        onTriggerEvent(stateEvent: RecipeListEvents.LoadRecipes())
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvents) {
        switch stateEvent {
        case is RecipeListEvents.LoadRecipes:
            loadRecipes()
        case is RecipeListEvents.NewSearch:
            doNothing()
        case is RecipeListEvents.NextPage:
            doNothing()
        case is RecipeListEvents.OnUpdateQuery:
            doNothing()
        case is RecipeListEvents.OnSelectCategory:
            doNothing()
        case is RecipeListEvents.OnRemoveHeadMessageFromQueue:
            doNothing()
        default:
            doNothing()
        }
    }
    
    private func loadRecipes() {
        let currentState = self.state.copy() as! RecipeListState
        do {
            try searchRecipes.execute(page: Int32(currentState.page), query: currentState.query)
                .collectCommon(coroutineScope: nil, callback: {dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false
                        self.updateState(isLoading: loading)
                        if data != nil {
                            self.appendRecipes(recipes: data as! [Recipe])
                        }
                        if message != nil {
                            self.handleMessageByUiComponentType(message!.build())
                        }
                    }
                })
        } catch {
            print("\(error)")
        }
    }
    
    func doNothing() {}
    
    func appendRecipes(recipes: [Recipe]) {
        var currentState = self.state.copy() as! RecipeListState
        var currentRecipes = currentState.recipes
        currentRecipes.append(contentsOf: recipes)
        self.state = self.state.doCopy(isLoading: currentState.isLoading, page: currentState.page, query: currentState.query, selectedCategory: currentState.selectedCategory, recipes: currentRecipes, queue: currentState.queue)
    }
    
    private func handleMessageByUiComponentType(_ message: GenericMessageInfo) {
        
    }
    
    func updateState(isLoading: Bool? = nil, page: Int? = nil, query: String? = nil, queue: Queue<GenericMessageInfo>? = nil) {
        let currentState = self.state.copy() as! RecipeListState
        self.state = self.state.doCopy(isLoading: isLoading ?? currentState.isLoading, page: Int32(page ?? Int(currentState.page)), query: query ?? currentState.query, selectedCategory: currentState.selectedCategory, recipes: currentState.recipes, queue: queue ?? currentState.queue)
    }
}
