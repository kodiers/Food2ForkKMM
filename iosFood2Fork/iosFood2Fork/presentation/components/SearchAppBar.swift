//
//  SearchAppBar.swift
//  iosFood2Fork
//
//  Created by Viktor Yamchinov on 13.04.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchAppBar: View {
    @State var query: String = ""
    
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "magnifyingglass")
                TextField("Search...", text: $query) {
                    // TODO: new search
                }
                .onChange(of: query, perform: { value in
                    // TODO: update query
                })
            }
            .padding(.bottom, 8)
        }
        .padding(.top, 8)
        .padding(.leading, 8)
        .padding(.trailing, 8)
        .background(Color.white.opacity(0))
    }
}

struct SearchAppBar_Previews: PreviewProvider {
    static var previews: some View {
        SearchAppBar()
    }
}
