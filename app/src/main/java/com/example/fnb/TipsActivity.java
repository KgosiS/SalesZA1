package com.example.fnb;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TipsActivity extends AppCompatActivity {

    private ArrayList<FinancialTip> financialTips;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


        financialTips = new ArrayList<>();
        financialTips.add(new FinancialTip("Track your expenses daily to know where your money goes."));
        financialTips.add(new FinancialTip("Create a budget and stick to it to manage your finances better."));
        financialTips.add(new FinancialTip("Save a portion of your income every month for future needs."));
        financialTips.add(new FinancialTip("Avoid unnecessary debt by using credit cards responsibly."));
        financialTips.add(new FinancialTip("Build an emergency fund to cover unexpected expenses."));
        financialTips.add(new FinancialTip("Invest in your education to increase your earning potential."));
        financialTips.add(new FinancialTip("Review your subscriptions and cancel those you don't use."));
        financialTips.add(new FinancialTip("Shop for groceries with a list to avoid impulse buys."));
        financialTips.add(new FinancialTip("Set financial goals to keep you motivated and on track."));
        financialTips.add(new FinancialTip("Pay off high-interest debt first to save on interest payments."));
        financialTips.add(new FinancialTip("Use cashback and reward programs to your advantage."));
        financialTips.add(new FinancialTip("Plan for retirement by contributing to a retirement account."));
        financialTips.add(new FinancialTip("Automate savings to ensure you consistently save each month."));
        financialTips.add(new FinancialTip("Avoid lifestyle inflation; live below your means even as your income increases."));
        financialTips.add(new FinancialTip("Use budgeting apps to track your spending and stay on top of your finances."));
        financialTips.add(new FinancialTip("Compare prices before making a purchase to ensure you get the best deal."));
        financialTips.add(new FinancialTip("Build and maintain a good credit score by paying bills on time."));
        financialTips.add(new FinancialTip("Avoid emotional spending by finding alternative ways to manage stress."));
        financialTips.add(new FinancialTip("Consider investing in index funds for long-term growth."));
        financialTips.add(new FinancialTip("Use a financial advisor if you need help with complex financial planning."));
        financialTips.add(new FinancialTip("Create a will to ensure your assets are distributed according to your wishes."));
        financialTips.add(new FinancialTip("Review your insurance policies regularly to ensure adequate coverage."));
        financialTips.add(new FinancialTip("Take advantage of employer benefits, such as matching retirement contributions."));
        financialTips.add(new FinancialTip("Negotiate bills and expenses to lower your monthly payments."));
        financialTips.add(new FinancialTip("Keep your financial documents organized and secure."));
        financialTips.add(new FinancialTip("Save for major purchases instead of financing them with credit."));
        financialTips.add(new FinancialTip("Use public transportation or carpool to reduce transportation costs."));
        financialTips.add(new FinancialTip("Set aside money for leisure and hobbies to avoid burnout from strict budgeting."));
        financialTips.add(new FinancialTip("Invest in home maintenance to prevent costly repairs in the future."));
        financialTips.add(new FinancialTip("Monitor your credit report for errors and fraudulent activity."));
        financialTips.add(new FinancialTip("Use coupons and discount codes when shopping online or in-store."));
        financialTips.add(new FinancialTip("Set up a separate savings account for specific goals, like a vacation or new car."));
        financialTips.add(new FinancialTip("Understand the terms of any loans or credit you take out to avoid surprises."));
        financialTips.add(new FinancialTip("Consider passive income sources to diversify your income streams."));
        financialTips.add(new FinancialTip("Learn about tax deductions and credits to reduce your tax liability."));
        financialTips.add(new FinancialTip("Avoid making large purchases on impulse; give yourself time to think it over."));
        financialTips.add(new FinancialTip("Use financial planning tools to help manage your investments and savings."));
        financialTips.add(new FinancialTip("Make extra payments on your mortgage to reduce interest and pay off the loan faster."));
        financialTips.add(new FinancialTip("Seek out financial literacy resources to improve your money management skills."));
        financialTips.add(new FinancialTip("Plan for major life events, such as starting a family or buying a home."));
        financialTips.add(new FinancialTip("Review and adjust your budget regularly to reflect changes in your financial situation."));
        financialTips.add(new FinancialTip("Save receipts and track your spending to identify areas where you can cut back."));
        financialTips.add(new FinancialTip("Invest in a diverse portfolio to spread risk and increase potential returns."));
        financialTips.add(new FinancialTip("Educate yourself about investment options before committing your money."));
        financialTips.add(new FinancialTip("Set up automatic bill payments to avoid late fees and missed payments."));
        financialTips.add(new FinancialTip("Consider refinancing high-interest loans to lower your payments."));
        financialTips.add(new FinancialTip("Use a financial tracker to keep an eye on your investments and savings goals."));
        financialTips.add(new FinancialTip("Make a habit of reviewing your financial goals and progress regularly."));
        financialTips.add(new FinancialTip("Learn about and take advantage of government financial assistance programs if needed."));
        financialTips.add(new FinancialTip("Use a financial planner or advisor for personalized financial advice and strategies."));
        financialTips.add(new FinancialTip("Stay informed about economic trends that might affect your financial situation."));


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TipsAdapter tipsAdapter = new TipsAdapter(financialTips);
        recyclerView.setAdapter(tipsAdapter);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Set up the Drawer Toggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_sales){
                    startActivity(new Intent(TipsActivity.this, SalesActivity.class));
                }else if (item.getItemId() == R.id.nav_tips){
                    startActivity(new Intent(TipsActivity.this, TipsActivity.class));
                }else if(item.getItemId() == R.id.nav_voucher){
                    startActivity(new Intent(TipsActivity.this, VouchersActivity.class));
                }else if(item.getItemId() == R.id.nav_watch_ads){
                    startActivity(new Intent(TipsActivity.this, WatchAdsActivity.class));
                }else if(item.getItemId() == R.id.nav_finance){
                    startActivity(new Intent(TipsActivity.this, FinancialLiteracyActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer after clicking
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}