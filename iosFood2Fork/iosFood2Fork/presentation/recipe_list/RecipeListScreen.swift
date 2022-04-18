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
    private let foodCategories: [FoodCategory]
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(networkModule: self.networkModule, cacheModule: self.cacheModule)
        let foodCategoryUtil = FoodCategoryUtil()
        self.viewModel = RecipeListViewModel(searchRecipes: searchRecipesModule.searchRecipes, foodCategoryUtil: foodCategoryUtil)
        self.foodCategories = foodCategoryUtil.getAllFoodCategories()
    }
    
    var body: some View {
        NavigationView {
            ZStack {
                VStack {
                    SearchAppBar(query: viewModel.state.query, foodCategories: foodCategories, selectedCategory: viewModel.state.selectedCategory) { event in
                        viewModel.onTriggerEvent(stateEvent: event)
                    }
                    List {
                        ForEach(viewModel.state.recipes, id: \.self.id) {recipe in
                            ZStack {
                                RecipeCard(recipe: recipe)
                                    .onAppear(perform: {
                                        if viewModel.shouldQueryNextPage(recipe: recipe) {
                                            viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                                        }
                                    })
                                NavigationLink(destination: RecipeDetailScreen(recipeId: Int(recipe.id), cacheModule: self.cacheModule)) {
                                    EmptyView()
                                }
                            }
                            .listRowInsets(EdgeInsets())
                            .padding(.top, 10)
                        }
                    }
                    .listStyle(PlainListStyle())
                    .navigationBarHidden(true)
                }
                if (viewModel.state.isLoading) {
                    ProgressView("Searching recipes...")
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
