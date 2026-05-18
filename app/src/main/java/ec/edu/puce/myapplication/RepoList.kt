package ec.edu.puce.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RepoList(modifier: Modifier = Modifier) {

    val repositories = listOf(
        "android-compose",
        "github-client",
        "jetpack-basics",
        "kotlin-course",
        "mobile-lab"
    )

    Column(
        modifier = modifier.padding(8.dp)
    ) {

        repositories.forEach { repo ->

            RepoItem(repoName = repo)

        }

    }
}