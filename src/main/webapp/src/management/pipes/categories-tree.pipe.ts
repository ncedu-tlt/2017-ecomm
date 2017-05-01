import {Pipe, PipeTransform} from "@angular/core";
import CategoryModel from "../models/category.model";


@Pipe({
    name: 'categoriesTree'
})

export class CategoriesTreePipe implements PipeTransform {
    transform(list: any[], id: string, parentId: string): any {
        let keys: any[] = [];
        list.map((l) => {
            l.children = [];
            keys.push(l[id]);
        });

        let roots: CategoryModel[] = list.filter((l) => keys.indexOf(l[parentId]) == -1);
        let nodes: any[] = [];
        roots.map((l) => nodes.push(l));

        while(nodes.length > 0){
            let node = nodes.pop();
            let children = list.filter((l) => l[parentId] == node[id]);

            children.map((l) => {
                node.children.push(l);
                nodes.push(l);
            });
        }

        if(roots.length == 1)
            return roots[0];

        return roots;
    }
}
