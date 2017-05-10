import {Pipe, PipeTransform} from "@angular/core";
import CategoryModel from "../models/category.model";


@Pipe({
    name: 'asTree'
})

export class AsTreePipe implements PipeTransform {
    transform(list: CategoryModel[], id: string, parentId: string): any {
        let keys: number[] = [];
        list.map((item) => {
            keys.push(item[id]);
        });

        let roots: CategoryModel[] = list.filter((l) => keys.indexOf(l[parentId]) == -1);
        let nodes: any[] = [];
        nodes = nodes.concat(roots);

        while(nodes.length > 0){
            let node = nodes.pop();
            let children = list.filter((l) => l[parentId] == node[id]);

            children.map((item) => {
                node.children.push(item);
                nodes.push(item);
            });
        }

        return roots;
    }
}
