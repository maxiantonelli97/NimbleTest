package com.antonelli.nimble.ui.home

import android.widget.Toast
import androidx.compose.foundation.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.antonelli.nimble.R
import com.antonelli.nimble.entity.SurveyItem
import com.antonelli.nimble.enums.StatesEnum
import com.antonelli.nimble.navigation.AppScreens
import com.antonelli.nimble.ui.loading.LoadingScreen

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val responseEnum by homeViewModel.responseEnum.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()

    if (isLoading == true) {
        LoadingScreen()
    }

    if (responseEnum != null) {
        when (responseEnum!!) {
            StatesEnum.SUCCESS -> {
                HomeConstraint(homeViewModel.getSurveysList(), navController, homeViewModel)
            }
            StatesEnum.ERROR -> {
                navController.navigate(AppScreens.ErrorScreen.route + "/${AppScreens.HomeScreen.route}")
                homeViewModel.responseEnum.value = null
            }
            StatesEnum.START -> {
                homeViewModel.getSurveys()
                homeViewModel.responseEnum.value = null
            }
            StatesEnum.NOAUTH -> {
                val contexto = LocalContext.current
                Toast.makeText(contexto, stringResource(id = R.string.login_error), Toast.LENGTH_LONG).show()
                navController.popBackStack()
                navController.navigate(AppScreens.LogInScreen.route)
                homeViewModel.responseEnum.value = null
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeConstraint(list: List<SurveyItem>, navController: NavController, homeViewModel: HomeViewModel) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )
    val pullRefreshState = rememberPullRefreshState(
        refreshing = homeViewModel.isRefreshing.value,
        onRefresh = {
            homeViewModel.isRefreshing.value = true
            homeViewModel.getSurveys()
        }
    )
    ConstraintLayout(
        modifier = Modifier.pullRefresh(pullRefreshState).fillMaxSize() // .verticalScroll(rememberScrollState())

        // ////////////////
        // ////////////////  BY ADDING THE COMMENT FUNCTION IN LINE 79, THE SCROLL'S UPDATE WORKS
        // ////////////////  BUT THIS BRAKE THE VIEW. AND I CONSIDERED IRRELEVANT
        // ////////////////

    ) {
        val (item, pager, arrow, out, refresh) = createRefs()
        PullRefreshIndicator(
            refreshing = homeViewModel.isRefreshing.value,
            state = pullRefreshState,
            modifier = Modifier.constrainAs(refresh) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        if (list.isEmpty()) {
            Text(
                text = "No hay encuestas disponibles actualmente",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        } else {
            HorizontalPager(
                state = pagerState,
                pageCount = list.size,
                modifier = Modifier.fillMaxSize().constrainAs(item) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) { page ->
                PagerConstraint(list[page])
            }
            Image(
                painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                contentDescription = "log out",
                modifier = Modifier.padding(10.dp, 50.dp, 10.dp, 10.dp).constrainAs(out) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }.clickable(
                    onClick = {
                        homeViewModel.logOut()
                    }
                )
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_circle_right_24),
                contentDescription = "arrow",
                modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 50.dp).constrainAs(arrow) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }.clickable(
                    onClick = {
                        navController.navigate(AppScreens.DetailScreen.route + "/${list[pagerState.currentPage].title}")
                    }
                )
            )
            Row(
                modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 300.dp).constrainAs(pager) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                repeat(list.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.White else Color(1f, 1f, 1f, 0.5f)
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PagerConstraint(item: SurveyItem) {
    ConstraintLayout() {
        val (tittle, description, image) = createRefs()
        AsyncImage(
            model = item.cover_image_url,
            contentDescription = item.cover_image_url,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.FillHeight
        )
        Text(
            text = item.title ?: "Tittle",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = Color.White,
            maxLines = 2,
            modifier = Modifier.padding(start = 15.dp, top = 450.dp).constrainAs(tittle) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = item.description ?: "Desc",
            fontSize = 20.sp,
            color = Color.White,
            maxLines = 2,
            modifier = Modifier.height(120.dp).padding(start = 15.dp, end = 120.dp).constrainAs(description) {
                top.linkTo(tittle.bottom)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}
