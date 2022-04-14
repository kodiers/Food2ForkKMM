//
//  RecipeDetailScreen.swift
//  iosFood2Fork
//
//  Created by Viktor Yamchinov on 15.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeDetailScreen: View {
    
    private let cacheModule: CacheModule
    private let getRecipeModule: GetRecipeModule
    private let recipeId: Int
    private let datetimeUtil = DateTimeUtil()
    
    @ObservedObject var viewModel: RecipeDetailViewModel
    
    init(
        recipeId: Int,
        cacheModule: CacheModule
         ) {
        self.recipeId = recipeId
        self.cacheModule = cacheModule
        self.getRecipeModule = GetRecipeModule(
            cacheModule: self.cacheModule
        )
        viewModel = RecipeDetailViewModel(
            recipeId: self.recipeId,
            getRecipe: self.getRecipeModule.getRecipe
        )
    }
    
    var body: some View {
        Text("\(recipeId)")
    }
}
