package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.ui.components.RepoItem
import ec.edu.puce.githubclient.viewmodels.RepoListViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import ec.edu.puce.githubclient.Models.Repository
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button

@Composable
fun RepoList(
    modifier: Modifier = Modifier,
    viewModel: RepoListViewModel = viewModel (),
    onNavigateToForm: () -> Unit = {}

) {
    val repos by viewModel.repos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errMsg by viewModel.errMsg.collectAsState()
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }
    var showEditForm by remember {
        mutableStateOf(false)
    }

    var selectedRepo by remember {
        mutableStateOf<Repository?>(null)
    }


    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToForm,
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir repositorio"
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            errMsg?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(all = 16.dp)
                )
            }
            if (!isLoading && errMsg == null) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(count = repos.size) { i ->
                        RepoItem(

                            repository = repos[i],

                            onEdit = {

                                selectedRepo = it
                                showEditForm = true

                            },
                            onDelete = {

                                selectedRepo = it
                                showDeleteDialog = true

                            }

                        )
                    }
                }
            }
            if (showDeleteDialog && selectedRepo != null) {

                AlertDialog(

                    onDismissRequest = {
                        showDeleteDialog = false
                    },

                    title = {
                        Text("Eliminar repositorio")
                    },

                    text = {
                        Text(
                            "¿Seguro que deseas eliminar ${selectedRepo!!.name}?"
                        )
                    },

                    confirmButton = {

                        Button(
                            onClick = {

                                viewModel.deleteRepository(
                                    selectedRepo!!.owner.login,
                                    selectedRepo!!.name
                                )

                                showDeleteDialog = false

                            }
                        ) {

                            Text("Eliminar")

                        }

                    },

                    dismissButton = {

                        Button(
                            onClick = {
                                showDeleteDialog = false
                            }
                        ) {

                            Text("Cancelar")

                        }

                    }

                )
                if (showEditForm && selectedRepo != null) {

                    RepoForm(

                        repository = selectedRepo,

                        onBackClick = {
                            showEditForm = false
                        },

                        onSaveSuccess = {

                            showEditForm = false

                            viewModel.fetchRepos()

                        },

                        onUpdateRepository = {
                                owner,
                                repo,
                                newName,
                                newDescription ->

                            viewModel.updateRepository(
                                owner,
                                repo,
                                newName,
                                newDescription
                            )

                        }

                    )

                }

            }
            if (showEditForm && selectedRepo != null) {

                RepoForm(

                    repository = selectedRepo,

                    onBackClick = {
                        showEditForm = false
                    },

                    onSaveSuccess = {

                        showEditForm = false

                        viewModel.fetchRepos()

                    },

                    onUpdateRepository = {
                            owner,
                            repo,
                            newName,
                            newDescription ->

                        viewModel.updateRepository(
                            owner,
                            repo,
                            newName,
                            newDescription
                        )

                    }

                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoListPreview () {
    GithubClientTheme () {
        RepoList()
    }
}

