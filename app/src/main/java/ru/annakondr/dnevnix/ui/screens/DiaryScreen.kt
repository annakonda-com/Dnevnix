package ru.annakondr.dnevnix.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.annakondr.dnevnix.R
import ru.annakondr.dnevnix.navigation.NavigationManager
import ru.annakondr.dnevnix.ui.components.ClassCard
import ru.annakondr.dnevnix.ui.entities.Lesson

val schedule = listOf(
    listOf(
        Lesson("Геометрия", "пункт 3 изучить"),
        Lesson("Обществознание", "параграф 5 конспект", done = true),
        Lesson("Химия", "§12, упр. 3, 5", done = true),
        Lesson("Русский язык", "Упр. 250, сочинение-рассуждение", done = true),
        Lesson("Биология", "§14, подготовить доклад", done = true),
        Lesson("Английский язык", "Текст стр. 90-91 перевести", done = true),
        Lesson("ОБЖ", "Подготовить сообщение о ЧС", done = true)
    ),
    listOf(
        Lesson("Физика", "§19, лабораторная №5", done = true),
        Lesson("Литература", "Анализ 1-ой главы 'Капитанской дочки'", done = true),
        Lesson("Алгебра", "№132, 135, 138"),
        Lesson("География", "§21, контурная карта стр. 15", done = true),
        Lesson("История", "§20, таблица 'Реформы Петра I'"),
        Lesson("Технология", "Подготовить материалы для проекта", done = true),
        Lesson("Физкультура", "Форма по погоде")
    ),
    listOf(
        Lesson("Геометрия", "пункт 4, №126, 129", done = true),
        Lesson("Русский язык", "Упр. 252, тестовая часть"),
        Lesson("Химия", "§13, ответить на вопросы", done = true),
        Lesson("Английский язык", "Диалог выучить наизусть"),
        Lesson("Биология", "§15, подготовиться к опросу"),
        Lesson("Обществознание", "§6, эссе на тему 'Гражданин'", done = true),
        Lesson("Музыка", "Подготовить биографию композитора")
    ),
    listOf(
        Lesson("Литература", "Прочитать 2-5 главы 'Капитанской дочки'"),
        Lesson("Физика", "§20, задачи №4, 5", done = true),
        Lesson("Алгебра", "Подготовка к контрольной работе"),
        Lesson("Информатика", "Практическая работа №3"),
        Lesson("География", "§22, подготовить презентацию", done = true),
        Lesson("История", "§21, термины выучить", done = true),
        Lesson("Классный час", "Тема: 'Планы на выходные'")
    ),
    listOf(null), listOf(null),
    listOf(
        Lesson("Алгебра", "№125, 128, 131"),
        Lesson("Русский язык", "Упр. 245, правила учить"),
        Lesson("Физика", "§18, ответить на вопросы"),
        Lesson("Английский язык", "Упр. 5, 6 стр. 84, слова учить"),
        Lesson("Литература", "Прочитать повесть 'Капитанская дочка'"),
        Lesson("История", "§19, конспект"),
        Lesson("Физкультура", "Форма по погоде")
    )
)


@Composable
fun DiaryScreenUi(modifier: Modifier, navigationManager: NavigationManager) {
    val pagerState = rememberPagerState(initialPage = 3, pageCount = { 7 })
    val animationScope = rememberCoroutineScope()
    val daysOfWeek = listOf("вт", "ср", "чт", "пт", "сб", "вс", "пн")
    Column(
        modifier
            .scrollable(rememberScrollState(), Orientation.Vertical)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (pagerState.currentPage > 0) {
                    animationScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.outline_arrow_back_ios_24),
                    contentDescription = ""
                )
            }
            Text(
                text = (14 + pagerState.currentPage).toString() + ".10.2025 " + daysOfWeek[pagerState.currentPage],
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(onClick = {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    animationScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.outline_arrow_forward_ios_24),
                    contentDescription = ""
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .fillMaxSize()
            ) {
                for (day in schedule[page]) {
                    if (day != null) {
                        if (day.done) {
                            ClassCard(
                                MaterialTheme.colorScheme.tertiaryContainer,
                                day.subject, day.task, R.drawable.check,
                                day,
                                navigationManager,
                            )
                        } else {
                            ClassCard(
                                MaterialTheme.colorScheme.surfaceContainerLowest,
                                day.subject, day.task, R.drawable.outline_arrow_right_24,
                                day,
                                navigationManager
                            )
                        }
                    } else {
                        Text(
                            "Выходной", style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }

        }
    }
}
