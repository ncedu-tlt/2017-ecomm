export default class CategoryModel{
    name: string;
    description: string;
    categoryId: number;
    parentId: number;
    children: CategoryModel[];
}
