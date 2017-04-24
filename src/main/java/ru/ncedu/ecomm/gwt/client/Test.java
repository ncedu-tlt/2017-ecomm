package ru.ncedu.ecomm.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.*;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import ru.ncedu.ecomm.gwt.shared.CategoryJSON;
import ru.ncedu.ecomm.gwt.shared.ProductJSO;

import java.util.List;

public class Test implements EntryPoint {
    private static final String JSON_URL_CATEGORIES = GWT.getHostPageBaseURL() + "rest/ecomm/categories";
    private static final String JSON_URL_PRODUCTS = GWT.getHostPageBaseURL() + "rest/ecomm/product";
    private static final int SUCCESS_ANSWER = 200;
    private static final int NULL_PARENT = 0;
    private static final int ALL_CATEGORIES = 0;
    private static final String TREE_PANEL = "tree";
    final ListDataProvider<ProductJSO> dataProviderProducts = new ListDataProvider<>();

    @Override
    public void onModuleLoad() {
        Tree tree = createTree();
        CellTable<ProductJSO> table = createTable(3);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(70);
        horizontalPanel.add(tree);
        horizontalPanel.add(table);

        RootPanel.get(TREE_PANEL).add(horizontalPanel);
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
                        mainRoot.setText("All");
                        mainRoot.getElement().setId(String.valueOf(ALL_CATEGORIES));
                        tree.addItem(mainRoot);
                        list.stream().filter(c -> c.getParentId() == NULL_PARENT).forEach(c -> {
                            TreeItem item = new TreeItem();
                            item.setText(c.getName());
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
                            updateProductDataProvider(Integer.parseInt(item.getElement().getId()));
                            dataProviderProducts.refresh();
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

    private CellTable<ProductJSO> createTable(int category) {
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

        dataProviderProducts.addDataDisplay(table);
        updateProductDataProvider(category);

        table.addColumn(idColumn, "Id");
        table.addColumn(nameColumn, "Name");
        table.addColumn(priceColumn, "Price");
        table.addColumn(descriptionColumn, "Description");

        return table;
    }

    private void updateProductDataProvider(final int category) {
        List<ProductJSO> list = dataProviderProducts.getList();

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, JSON_URL_PRODUCTS);
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert("Couldn't retrieve JSON");
                }

                public void onResponseReceived(Request request, Response response) {
                    if (SUCCESS_ANSWER == response.getStatusCode()) {
                        JsArray<ProductJSO> products = JsonUtils.safeEval(response.getText());

                        for (int i = 0; i < products.length(); i++) {
                            if (category == ALL_CATEGORIES) list.add(products.get(i));
                            else if (products.get(i).getCategoryId() == category) list.add(products.get(i));
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
}