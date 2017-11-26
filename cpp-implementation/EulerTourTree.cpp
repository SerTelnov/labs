// EulerTourTree.cpp : Defines the entry point for the console application.
//

//#include "stdafx.h"
#include <iostream>
#include <string>
#include <unordered_map>

using namespace std;

const int MAX_VALUE = 100010;

struct node {
	node(int name)
		: name(name)
		, left(nullptr)
		, right(nullptr)
		, parent(nullptr)
		, size(1)
		, prior(rand())
	{ }

	void update() {
		size = 1;
		if (left) {
			size += left->size;
		} 
		if (right) {
			size += right->size;
		}
	}

	int size, prior, name;
	node*left, * right, * parent;
};

struct node_pair {
	node_pair()
		: node_pair(nullptr, nullptr)
	{ }
	node_pair(node*first, node*second)
		: first(first)
		, second(second)
	{ }

	void set_parents(node*pfirst, node*psecond) {
		if (first) {
			first->parent = pfirst;
		}
		if (second) {
			second->parent = psecond;
		}
	}
	node*first, * second;
};

node* vertexes[MAX_VALUE];
unordered_map <int, unordered_map <int, node *>> edges;

void fill_vertex(const int n) {
	for (int i = 0; i != n; ++i) {
		vertexes[i] = new node(i);
	}
}

int get_node_size(node*v) {
	return v ? v->size : 0;
}

node*merge(node*left, node*right) {
	if (!left) {
		return right;
	}
	else if (!right) {
		return left;
	}
	if (left->prior > right->prior) {
		node*new_tree = merge(left->right, right);
		left->right = new_tree;
		new_tree->parent = left;
		left->update();
		return left;
	}
	else {
		node*new_tree = merge(left, right->left);
		right->left = new_tree;
		new_tree->parent = right;
		right->update();
		return right;
	}
}

node_pair split(node*root, const int key) {
	if (!root) {
		return node_pair();
	}
	const int left_size = get_node_size(root->left);
	if (left_size >= key) {
		node_pair sub = split(root->left, key);
		root->left = sub.second;
		root->update();
		sub.set_parents(nullptr, root);
		return node_pair(sub.first, root);
	}
	else {
		node_pair sub = split(root->right, key - left_size - 1);
		root->right = sub.first;
		root->update();
		sub.set_parents(root, nullptr);
		return node_pair(root, sub.second);
	}
}

node*get_last_node(node*v) {
	while (v->right) {
		v = v->right;
	}
	return v;
}

node*get_first_node(node*v) {
	while (v->left) {
		v = v->left;
	}
	return v;
}

node*get_root(node*v) {
	while (v->parent) {
		v = v->parent;
	}
	return v;
}

int get_vertex_index(node*curr) {
	int result = get_node_size(curr->left) + 1;
	while (curr->parent) {
		node*parent = curr->parent;
		if (parent->right && parent->right == curr) {
			result += get_node_size(parent->left) + 1;
		}
		curr = parent;
	}
	return result;
}

void link(int u, int v) {
	node*first = vertexes[u];
	node*second = vertexes[v];
	node_pair A = split(
		get_root(first),
		get_vertex_index(first) - 1
	);
	first = new node(first->name);
	A.first = merge(A.first, first);

	node_pair B = split(
		get_root(second),
		get_vertex_index(second) - 1
	);
	node*newB = new node(second->name);
	if (B.first) {
		B.first = split(
			merge(B.first, newB),
			1
		).second;
		node*lastInBRight = get_last_node(B.second);
		if (lastInBRight) {
			vertexes[lastInBRight->name] = lastInBRight;
			edges[lastInBRight->name][get_first_node(B.first)->name] = lastInBRight;
		} 
		edges[v][u] = newB;
	}
	else {
		edges[v][u] = get_last_node(B.second);
	}
	edges[u][v] = first;
	A.first = merge(A.first, B.second);
	A.first = merge(A.first, B.first);
	A.first = merge(A.first, A.second);
}

void cut(int u, int v) {
	node*first = edges[u][v];
	node*second = edges[v][u];
	int firstIndex = get_vertex_index(first);
	int secondIndex = get_vertex_index(second);
	
	if (firstIndex > secondIndex) {
		swap(first, second);
		swap(firstIndex, secondIndex);
	}

	node_pair subFirst = split(
		get_root(first),
		firstIndex
	);
	node_pair subSecond = split(
		subFirst.second,
		get_vertex_index(second)
	);
	node_pair subThird = split(
		subSecond.second, 1);
	subSecond.second = subThird.second;
	if (subThird.first) {
		vertexes[subThird.first->name] = first;
		if (subThird.second) {
			node*vertex = get_first_node(subThird.second);
			edges[subThird.first->name][vertex->name] = first;
		}
	}
	merge(subFirst.first, subSecond.second);
}

bool is_connect(int u, int v) {
	return get_root(vertexes[u]) == get_root(vertexes[v]);
}

int get_size(int u) {
	return (get_root(vertexes[u])->size) / 2 + 1;
}


int main()
{
	int n, m;
	cin >> n >> m;
	fill_vertex(n);
	for (int i = 0; i != m; ++i) {
		string command;
		cin >> command;
		int u, v;
		cin >> u;
		--u;
		if (command == "size") {
			cout << get_size(u) << '\n';
		}
		else {
			cin >> v;
			--v;
			if (command == "link") {
				link(u, v);
			}
			else if (command == "cut") {
				cut(u, v);
			}
		}
	}
    return 0;
}

