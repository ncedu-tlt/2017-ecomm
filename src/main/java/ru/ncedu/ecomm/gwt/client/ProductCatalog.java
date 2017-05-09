package ru.ncedu.ecomm.gwt.client;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import ru.ncedu.ecomm.gwt.shared.CategoryJSON;
import ru.ncedu.ecomm.gwt.shared.ProductJSO;

import java.util.List;

import static com.google.gwt.view.client.DefaultSelectionEventManager.createCheckboxManager;

public class ProductCatalog implements EntryPoint {
    private static final String JSON_URL_CATEGORIES = GWT.getHostPageBaseURL() + "rest/ecomm/categories";
    private static final String JSON_URL_ALL_PRODUCTS = GWT.getHostPageBaseURL() + "rest/ecomm/product";
    private static final String JSON_URL_CHILDREN_PRODUCTS = GWT.getHostPageBaseURL() + "rest/ecomm/v2/product/childrenProducts/";
    private static final int SUCCESS_ANSWER = 200;
    private static final int NULL_PARENT = 0;
    private static final int ALL_CATEGORIES = 0;
    private static final String MAIN_DIV = "productTable";
    private final ListDataProvider<ProductJSO> dataProviderProducts = new ListDataProvider<>();
    private final SelectionModel<ProductJSO> selectionModel = new MultiSelectionModel<>(dataProviderProducts);
    private Button menuButton = new Button("Menu", (ClickHandler) clickEvent -> {

    });

    @Override
    public void onModuleLoad() {
        ListHandler<ProductJSO> sortHandler = new ListHandler<>(dataProviderProducts.getList());
        CellTable<ProductJSO> productTable = createTable(selectionModel, sortHandler);

        Tree tree = createTree();


        Label productListLabel = new Label("Product List");

        Button editCategoryButton = new Button("Go to edit category", (ClickHandler) clickEvent -> {

        });
        Button addButton = new Button("ADD", (ClickHandler) clickEvent -> {

            FormPanel formPanel = createProductDetailForm();
            RootPanel.get(MAIN_DIV).clear();
            RootPanel.get(MAIN_DIV).add(formPanel);
        });
        Button updateButton = new Button("UPDATE", (ClickHandler) clickEvent -> {

        });
        Button deleteButton = new Button("DELETE", (ClickHandler) clickEvent -> {

        });

        HorizontalPanel hpButtons = new HorizontalPanel();
        HorizontalPanel hpManageButtons = new HorizontalPanel();
        HorizontalPanel hpTree = new HorizontalPanel();
        VerticalPanel mainPanel = new VerticalPanel();

        hpButtons.setWidth("1800px");
        hpButtons.setBorderWidth(2);
        hpButtons.add(menuButton);
        hpButtons.add(editCategoryButton);
        hpButtons.add(productListLabel);
        hpButtons.setCellWidth(menuButton, "50px");

        hpManageButtons.add(addButton);
        hpManageButtons.add(updateButton);
        hpManageButtons.add(deleteButton);

        hpButtons.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        hpButtons.add(hpManageButtons);

        hpTree.setSpacing(50);
        hpTree.add(tree);
        hpTree.add(productTable);

        mainPanel.add(hpButtons);
        mainPanel.add(hpTree);

        RootPanel.get(MAIN_DIV).add(mainPanel);
    }

    private Tree createTree() {
        ListDataProvider<CategoryJSON> dataProvider = new ListDataProvider<>();
        List<CategoryJSON> list = dataProvider.getList();
        Tree tree = new Tree();

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_CATEGORIES);
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSON");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (SUCCESS_ANSWER == response.getStatusCode()) {
                        JsArray<CategoryJSON> categories = JsonUtils.safeEval(response.getText());

                        for (int i = 0; i < categories.length(); i++) {
                            list.add(categories.get(i));
                        }

                        TreeItem mainRoot = new TreeItem();
                        mainRoot.setText("All Categories");
                        mainRoot.getElement().setId(String.valueOf(ALL_CATEGORIES));
                        tree.addItem(mainRoot);
                        list.stream().filter(c -> c.getParentId() == NULL_PARENT).forEach(c -> {
                            TreeItem item = new TreeItem();
                            item.setText(c.getName());
                            item.getElement().setId(String.valueOf(c.getCategoryId()));
                            mainRoot.addItem(item);
                            list.stream().filter(c1 -> c1.getParentId() == c.getCategoryId()).forEach(c1 -> {
                                TreeItem childItem = new TreeItem();
                                childItem.setText(c1.getName());
                                item.addItem(childItem);
                                childItem.getElement().setId(String.valueOf(c1.getCategoryId()));
                            });
                            tree.addItem(mainRoot);
                        });

                        tree.addSelectionHandler(selectionEvent -> {
                            TreeItem item = selectionEvent.getSelectedItem();
                            dataProviderProducts.getList().clear();
                            if (Integer.parseInt(item.getElement().getId()) == 0) {
                                List<ProductJSO> list = dataProviderProducts.getList();
                                RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_ALL_PRODUCTS);

                                fillList(list, builder);
                            }
                            else updateProductDataProvider(Integer.parseInt(item.getElement().getId()));
                        });

                    } else {
                        Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
                    }
                }
            });
        } catch (RequestException e) {
            Window.alert("Couldn't retrieve JSON (" + e.getMessage() + ")");
        }
        return tree;
    }

    private CellTable<ProductJSO> createTable(final SelectionModel<ProductJSO> selectionModel,
                                              ListHandler<ProductJSO> sortHandler) {
        CellTable<ProductJSO> table = new CellTable<>();

        TextColumn<ProductJSO> idColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return String.valueOf(product.getId());
            }
        };
        idColumn.setSortable(true);
        TextColumn<ProductJSO> nameColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return product.getName();
            }
        };
        TextColumn<ProductJSO> priceColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return String.valueOf(product.getPrice());
            }
        };
        TextColumn<ProductJSO> descriptionColumn = new TextColumn<ProductJSO>() {
            @Override
            public String getValue(ProductJSO product) {
                return product.getDescription();
            }
        };

        Column<ProductJSO, Boolean> checkColumn = new Column<ProductJSO, Boolean>(
                new CheckboxCell(true, false)) {
            @Override
            public Boolean getValue(ProductJSO object) {
                return selectionModel.isSelected(object);
            }
        };

        dataProviderProducts.addDataDisplay(table);
        List<ProductJSO> list = dataProviderProducts.getList();
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_ALL_PRODUCTS);

        fillList(list, builder);

        ListHandler<ProductJSO> idColumnSortHandler = new ListHandler<>(dataProviderProducts.getList());
        idColumnSortHandler.setComparator(idColumn,
                (ProductJSO o1, ProductJSO o2) -> {
                    if (o1 == o2) {
                        return 0;
                    }

                    if (o1 != null) {
                        return (o2 != null) ? Double.compare(o1.getId(), o2.getId()) : 1;
                    }
                    return -1;
                });
        table.addColumnSortHandler(idColumnSortHandler);
        table.getColumnSortList().push(idColumn);

        table.addColumn(idColumn, "Id");
        table.addColumn(nameColumn, "Name");
        table.addColumn(priceColumn, "Price");
        table.addColumn(descriptionColumn, "Description");
        table.addColumn(checkColumn);

        table.addColumnSortHandler(sortHandler);
        table.setSelectionModel(selectionModel, createCheckboxManager());

        return table;
    }

    private void updateProductDataProvider(final int category) {
        List<ProductJSO> list = dataProviderProducts.getList();
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_CHILDREN_PRODUCTS + String.valueOf(category));

        fillList(list, builder);
    }

    private void fillList(List<ProductJSO> list, RequestBuilder builder) {
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSON");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (SUCCESS_ANSWER == response.getStatusCode()) {
                        JsArray<ProductJSO> products = JsonUtils.safeEval(response.getText());

                        for (int i = 0; i < products.length(); i++) {
                            list.add(products.get(i));
                        }

                    } else {
                        Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
                    }
                }
            });
        } catch (RequestException e) {
            Window.alert("Couldn't retrieve JSON (" + e.getMessage() + ")");
        }
    }

    private FormPanel createProductDetailForm() {
        FormPanel formPanel = new FormPanel();
        formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        formPanel.setMethod(FormPanel.METHOD_POST);

        VerticalPanel vpMain = new VerticalPanel();
        vpMain.setSpacing(20);
        formPanel.setWidget(vpMain);

        TextBox category = new TextBox();
        category.setText("Category");
        TextBox name = new TextBox();
        name.setText("Name");
        TextArea description = new TextArea();
        description.setText("Description");

        vpMain.add(menuButton);
        vpMain.add(category);
        vpMain.add(name);
        vpMain.add(description);

        return formPanel;
    }
}