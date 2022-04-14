//
//  RecipeDetailViewModel.swift
//  iosFood2Fork
//
//  Created by Viktor Yamchinov on 15.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeDetailViewModel: ObservableObject {
    private let getRecipe: GetRecipe
    @State var state: RecipeDetailState = RecipeDetailState()
    
    init(recipeId: Int, getRecipe: GetRecipe) {
        self.getRecipe = getRecipe
        
    }
}
