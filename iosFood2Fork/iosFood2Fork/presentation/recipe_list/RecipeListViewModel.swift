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
        
    }
    
    func updateState(isLoading: Bool? = nil, page: Int? = nil, query: String? = nil, queue: Queue<GenericMessageInfo>? = nil) {
        let currentState = self.state.copy() as! RecipeListState
        self.state = self.state.doCopy(isLoading: isLoading ?? currentState.isLoading, page: Int32(page ?? Int(currentState.page)), query: query ?? currentState.query, selectedCategory: currentState.selectedCategory, recipes: currentState.recipes, queue: queue ?? currentState.queue)
    }
}
