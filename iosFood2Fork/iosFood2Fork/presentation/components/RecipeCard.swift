//
//  RecipeCard.swift
//  iosFood2Fork
//
//  Created by Viktor Yamchinov on 15.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeCard: View {
    
    private let recipe: Recipe
    
    init(recipe: Recipe) {
        self.recipe = recipe
    }
    
    var body: some View {
        VStack {
            WebImage(url: URL(string: recipe.featuredImage))
                .resizable()
                .placeholder(Image(systemName: "photo"))
                .placeholder {
                    Rectangle().foregroundColor(.white)
                }
                .indicator(.activity)
                .transition(.fade(duration: 0.5))
                .scaledToFill()
                .frame(height: 250, alignment: .center)
            .clipped()
            
            HStack(alignment: .lastTextBaseline) {
                Text(recipe.title)
                    .font(.body)
                    .frame(alignment: .center)
                Spacer()
                Text(String(recipe.rating))
                    .frame(alignment: .trailing)
            }
            .padding(.top, 8)
            .padding(.leading, 8)
            .padding(.trailing, 8)
            .padding(.bottom, 12)
        }
        .background(Color.white)
        .cornerRadius(8)
        .shadow(radius: 5)
    }
}

//struct RecipeCard_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeCard()
//    }
//}
