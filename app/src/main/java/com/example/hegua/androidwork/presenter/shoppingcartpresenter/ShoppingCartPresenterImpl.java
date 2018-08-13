package com.example.hegua.androidwork.presenter.shoppingcartpresenter;

import android.content.Context;

import com.example.hegua.androidwork.View.MyShoppingCartView;
import com.example.hegua.androidwork.View.ShowGoodsView;
import com.example.hegua.androidwork.object.shoppingcart_object.ShoppingCart;
import com.example.hegua.androidwork.object.trade_object.Trade;
import com.example.hegua.androidwork.presenter.asynctask.AddShoppingCartAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.BuyShoppingCartGoodsAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.DeleteShoppingCartAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.FabulousGoodsAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.FindShoppingCartTradeAsyncTask;
import com.example.hegua.androidwork.presenter.asynctask.FindUserAllShoppingCartAsyncTask;
import com.example.hegua.androidwork.utils.Constant;

/**
 * Created by hegua on 2018/8/7.
 */

public class ShoppingCartPresenterImpl implements ShoppingCartPresenter{

    @Override
    public void addShoppingCart(ShoppingCart shoppingCart, ShowGoodsView showGoodsView) {
        String data = "?username="+shoppingCart.getUsername()+"&name_merchant="+shoppingCart.getName_merchant()
                +"&number="+shoppingCart.getNumber();
        String url = Constant.URL + Constant.ADDSHOPPINGCART + data;

        AddShoppingCartAsyncTask addShoppingCartAsyncTask = new AddShoppingCartAsyncTask(url,showGoodsView);
        addShoppingCartAsyncTask.execute();
    }

    @Override
    public void getShoppingCartData(String username, MyShoppingCartView myShoppingCartView) {
        String data = "?username="+username;
        String url = Constant.URL+Constant.FINDUSERALLSC+data;

        FindUserAllShoppingCartAsyncTask findUserAllShoppingCartAsyncTask = new FindUserAllShoppingCartAsyncTask(url,myShoppingCartView);
        findUserAllShoppingCartAsyncTask.execute();
    }

    @Override
    public Trade setShoppingCartTrade(String name, String merchant, MyShoppingCartView myShoppingCartView, Context mContext) {
        String data = "?name="+name+"&merchant="+merchant;
        String url = Constant.URL + Constant.FINDONETRADE + data;

        FindShoppingCartTradeAsyncTask findShoppingCartTradeAsyncTask = new FindShoppingCartTradeAsyncTask(url,myShoppingCartView,mContext);
        findShoppingCartTradeAsyncTask.execute();
        return null;
    }

    @Override
    public void deleteShoppingCart(int id,MyShoppingCartView myShoppingCartView) {
        String data = "?id=" + id;
        String url = Constant.URL+Constant.DELETESC+data;
        DeleteShoppingCartAsyncTask deleteShoppingCartAsyncTask = new DeleteShoppingCartAsyncTask(url,myShoppingCartView);
        deleteShoppingCartAsyncTask.execute();
    }

    @Override
    public void buyShoppingCartGoods(ShoppingCart shoppingCart, MyShoppingCartView myShoppingCartView) {
        String data = "?name_merchant="+shoppingCart.getName_merchant()+"&number="+shoppingCart.getNumber();
        String url = Constant.URL+Constant.BUYTRADE+data;

        myShoppingCartView.buyShoppingCartTradeSuccess("提交成功");
        BuyShoppingCartGoodsAsyncTask buyShoppingCartGoodsAsyncTask = new BuyShoppingCartGoodsAsyncTask(url,myShoppingCartView);
        buyShoppingCartGoodsAsyncTask.execute();
    }

    @Override
    public void fabulousShoppingCartGoods(String name_merchant,int fabulous, MyShoppingCartView myShoppingCartView) {
        String data = "?name_merchant="+name_merchant+"&fabulous="+fabulous;
        String url = Constant.URL+Constant.FABULOUSTRADE+data;

        System.out.println("fabulousShoppingCartGoods---------------->"+url);
        FabulousGoodsAsyncTask fabulousGoodsAsyncTask = new FabulousGoodsAsyncTask(url,myShoppingCartView);
        fabulousGoodsAsyncTask.execute();
    }
}
