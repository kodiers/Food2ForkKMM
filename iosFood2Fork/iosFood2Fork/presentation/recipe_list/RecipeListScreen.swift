//
//  RecipeListScreen.swift
//  iosFood2Fork
//
//  Created by Viktor Yamchinov on 08.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(networkModule: self.networkModule, cacheModule: self.cacheModule)
        self.viewModel = RecipeListViewModel(searchRecipes: searchRecipesModule.searchRecipes, foodCategoryUtil: FoodCategoryUtil())
    }
    
    var body: some View {
        VStack {
            HStack {
                Text("Page: \(viewModel.state.page), Size: \(viewModel.state.recipes.count)")
                    .padding()
            }
            SearchAppBar()
            List {
                ForEach(viewModel.state.recipes, id: \.self.id) {recipe in
                    Text(recipe.title)
                        .onAppear(perform: {
                            if viewModel.shouldQueryNextPage(recipe: recipe) {
                                viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                            }
                        })
                }
            }
        }
    }
}

//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
